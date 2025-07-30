import { Component } from '@angular/core';
import { AgendamentoManual } from '../../models/agendamentomanual.model';
import { Observable } from 'rxjs';
import { AgendamentoService } from '../../services/agendamento.service';
import { MatTableModule } from '@angular/material/table';
import { CommonModule, NgFor } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { HeaderComponent } from "../../components/header/header.component";
import { ActivatedRoute, Router } from '@angular/router';
import { AgendamentoAutomatico } from '../../models/agendamentoautomatico.model';
import { BotaoComponent } from "../../components/botao/botao.component";
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-lista-agendamentos',
  imports: [MatTableModule, MatFormFieldModule, MatInputModule, NgFor, CommonModule, HeaderComponent, BotaoComponent],
  templateUrl: './lista-agendamentos.component.html',
  styleUrl: './lista-agendamentos.component.scss'
})

export class ListaAgendamentosComponent {

  agendamentos$ = new Observable<AgendamentoManual[]>();
  agendamentosauto$ = new Observable<AgendamentoAutomatico[]>();
  agendamentos: AgendamentoManual | null = null;

  constructor(private agendamentoService: AgendamentoService, private router: Router, private snackBar: MatSnackBar, private route: ActivatedRoute,){
    this.listarAgendamentosManuais();
    this.listarAgendamentosAutomaticos();
  } 
  
  listarAgendamentosManuais() {
    this.agendamentos$ = this.agendamentoService.listarAgendamentosManuais()
  }

  listarAgendamentoManualPorId(id: number) {
    this.agendamentoService.listarAgendamentoManualPorId(id);
  }

  listarAgendamentosAutomaticos() {
    this.agendamentosauto$ = this.agendamentoService.listarAgendamentosAutomaticos()
  }

  deletar(id: number) {
    const snackBarRef = this.snackBar.open('Deseja excluir cadastro?', 'Sim', {
      duration: 10000
    });
  
    snackBarRef.onAction().subscribe(() => {
      this.agendamentoService.deletarAgendamento(id).subscribe(_ => this.listarAgendamentosManuais());
      this.snackBar.open('Cadastro excluído com sucesso!', 'Fechar', {
        duration: 5000
      });
    });
    
    snackBarRef.afterDismissed().subscribe(info => {
      if (!info.dismissedByAction) {
        this.snackBar.open('Exclusão cancelada!', 'Fechar', {
          duration: 5000
        });
      }
    });
  }

  deletarAuto(id: number) {
    const snackBarRef = this.snackBar.open('Deseja excluir cadastro?', 'Sim', {
      duration: 10000
    });
  
    snackBarRef.onAction().subscribe(() => {
      this.agendamentoService.deletarAgendamentoAutomático(id).subscribe(_ => this.listarAgendamentosAutomaticos());
      this.snackBar.open('Agendamento excluído com sucesso!', 'Fechar', {
        duration: 5000
      });
    });
    
    snackBarRef.afterDismissed().subscribe(info => {
      if (!info.dismissedByAction) {
        this.snackBar.open('Exclusão cancelada!', 'Fechar', {
          duration: 5000
        });
      }
    });
  }

  goNotificacoes(): void {
    this.router.navigate(['/lista-notificacoes']);
  }

  alterar(id: number): void {
    if (id) {
      console.log('Navegando para edição com ID:', id);
      this.router.navigate(['/agendamento-edit', id]);
    } else {
      console.error('ID do agendamento não disponível para edição');
      this.snackBar.open('ID do agendamento não disponível', 'Fechar', { duration: 3000 });
    }
  }

  voltar(): void {
    this.router.navigate(['']);
  }

}

import { Component } from '@angular/core';
import { BotaoComponent } from "../../components/botao/botao.component";
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Notificacao } from '../../models/notificacao.model';
import { NotificacaoService } from '../../services/notificacao.service';
import { CommonModule, NgFor } from '@angular/common';

@Component({
  selector: 'app-lista-notificacoes',
  imports: [BotaoComponent, NgFor, CommonModule],
  templateUrl: './lista-notificacoes.component.html',
  styleUrl: './lista-notificacoes.component.scss'
})
export class ListaNotificacoesComponent {

  notificacoes$ = new Observable<Notificacao[]>();

  constructor(private notificacaoService: NotificacaoService, private router: Router) {
    this.listarNotificacoes();
  }

  voltar(): void {
    this.router.navigate(['/lista-agendamentos']);
  }

  listarNotificacoes() {
    this.notificacoes$ = this.notificacaoService.listarNotificacoes();
  }
}

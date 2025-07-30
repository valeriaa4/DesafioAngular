import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { DefaultPageLayoutComponent } from '../../components/default-page-layout/default-page-layout.component';
import { CommonModule, NgFor } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AgendamentoService } from '../../services/agendamento.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { CadastroService } from '../../services/cadastro.service';
import { Cadastro } from '../../models/cadastro.model';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agendamento',
  providers: [provideNativeDateAdapter()],
  imports: [DefaultPageLayoutComponent, 
    ReactiveFormsModule, 
    CommonModule, 
    MatFormFieldModule, 
    MatInputModule, 
    MatIconModule, 
    MatSelectModule, 
    MatDatepickerModule, 
    NgFor],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './agendamento.component.html',
  styleUrl: './agendamento.component.scss'
})

export class AgendamentoComponent implements OnInit {
  agendamentoForm!: FormGroup

  cadastros$ = new Observable<Cadastro[]>();

  constructor(private agendamentoService: AgendamentoService, 
    private cadastroService: CadastroService, 
    private snackBar: MatSnackBar, 
    private router: Router){
    this.listarCadastros();
  }

  ngOnInit(): void {
    this.agendamentoForm = new FormGroup({
       idPet: new FormControl([Validators.required, Validators.min(0)]),
       data: new FormControl([Validators.required]),
       horario: new FormControl([Validators.required]),
       cuidado: new FormControl('', [Validators.required]),
    });
  }

  submit(){
    if(this.agendamentoForm.valid) {
      this.agendamentoService.agendarCuidado(this.agendamentoForm.value).subscribe({
        next: (response) => console.log('Agendamento realizado com sucesso!', 
            this.snackBar.open('Cuidado agendado com sucesso!', 'Fechar', {
              duration: 5000
            }),
            this.agendamentoForm.reset()),
        error: (error) => console.error('Erro ao agendar cuidado', error,
          this.snackBar.open('Erro ao agendar cuidado.', 'Fechar', {
            duration: 5000
          })),
      });
    }
  }

  listarCadastros(){
    this.cadastros$ = this.cadastroService.listarCadastros();
  }
}
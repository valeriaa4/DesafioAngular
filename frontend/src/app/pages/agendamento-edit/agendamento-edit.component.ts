import { CommonModule} from '@angular/common';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { BotaoComponent } from '../../components/botao/botao.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AgendamentoService } from '../../services/agendamento.service';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';

@Component({
  selector: 'app-agendamento-edit',
  providers: [provideNativeDateAdapter()],
  imports: [ReactiveFormsModule,
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    MatRadioModule,
    MatSelectModule, 
    BotaoComponent,
    MatDatepickerModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './agendamento-edit.component.html',
  styleUrl: './agendamento-edit.component.scss'
})

export class AgendamentoEditComponent implements OnInit{

  id: number | null = null;
  agendamentoEditForm!: FormGroup;
  carregando = true;
  errorMessage: string | null = null;

  constructor(private agendamentoService: AgendamentoService, 
    private route: ActivatedRoute, 
    private snackBar: MatSnackBar, 
    private router: Router) {}

  ngOnInit(): void {
    this.inicializarForm();
    this.route.paramMap.subscribe(params => {
      this.id = Number(params.get('id'));
      console.log('ID recebido na edição:', this.id);
  
      if (this.id) {
        this.agendamentoService.listarAgendamentoManualPorId(this.id).subscribe({
          next: (agendamento) => {
            console.log('Dados recebidos da API:', agendamento);
            if (agendamento) {
              this.agendamentoEditForm.patchValue(agendamento);
              this.carregando = false;
            } else {
              this.errorMessage = 'Agendamento não encontrado na API';
              this.carregando = false;
            }
          },
          error: (error) => {
            console.error('Erro na requisição:', error);
            this.errorMessage = 'Erro ao carregar dados do agendamento';
            this.carregando = false;
          }
        });
      } else {
        this.errorMessage = 'ID inválido';
        this.carregando = false;
      }
    });
  }

  carregarDadosAgendamento(): void {
    this.agendamentoService.listarAgendamentoManualPorId(this.id!).subscribe({
      next: (agendamento) => {
        this.agendamentoEditForm.patchValue(agendamento);
        this.carregando = false;
      },
      error: (error) => {
        this.errorMessage = 'Erro ao carregar dados do agendamento';
        console.error('Erro:', error);
        this.carregando = false;
        this.snackBar.open('Erro ao carregar dados do agendamento', 'Fechar', {
          duration: 5000
        });
      }
    });
  }

  inicializarForm(): void {
    this.agendamentoEditForm = new FormGroup({
      id: new FormControl({value: '', disabled: true}),
      idPet: new FormControl('', [Validators.required]),
      nomeTutor: new FormControl('', [Validators.required]),
      emailTutor: new FormControl('', [Validators.required]),
      nomePet: new FormControl('', [Validators.required]),
      raca: new FormControl('', [Validators.required]),
      data: new FormControl('', [Validators.required]),
      horario: new FormControl('', [Validators.required]),
      cuidado: new FormControl('', [Validators.required])
    });
  }

  salvar(): void {
    if (this.agendamentoEditForm.valid && this.id) {
      this.carregando = true;
      
      const dadosAtualizados = {
        ...this.agendamentoEditForm.value,
        id: this.id
      };
  
      this.agendamentoService.alterarAgendamento(this.id, dadosAtualizados).subscribe({
        next: () => {
          this.snackBar.open('Agendamento atualizado com sucesso!', 'Fechar', {
            duration: 5000
          });
          this.router.navigate(['/lista-agendamentos']);
        },
        error: (error) => {
          console.error('Erro ao atualizar:', error);
          this.snackBar.open('Erro ao atualizar agendamento', 'Fechar', {
            duration: 5000
          });
          this.carregando = false;
        }
      });
    } else {
      this.marcarCamposComoSujos();
    }
  }
  
    marcarCamposComoSujos(): void {
      Object.values(this.agendamentoEditForm.controls).forEach(control => {
        control.markAsTouched();
      });
    }
  
    voltar(): void {
      if (this.id) {
        this.router.navigate(['/lista-agendamentos', this.id]);
      } else {
        this.router.navigate(['/lista-agendamentos']);
      }
    }

  cancelar(){
    this.router.navigate(['/lista-agendamentos']);
  }
}

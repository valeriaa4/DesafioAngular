import { CommonModule, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { CadastroService } from '../../services/cadastro.service';
import { BotaoComponent } from "../../components/botao/botao.component";
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-cadastro-edit',
  imports: [ReactiveFormsModule,
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    MatRadioModule,
    MatSelectModule, 
    BotaoComponent],
  templateUrl: './cadastro-edit.component.html',
  styleUrl: './cadastro-edit.component.scss'
})

export class CadastroEditComponent {

  id: number | null = null;
  cadastroEditForm!: FormGroup;
  carregando = true;
  errorMessage: string | null = null;

  constructor(
    private cadastroService: CadastroService,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.inicializarForm();
    this.route.paramMap.subscribe(params => {
      this.id = Number(params.get('id'));
      console.log('ID recebido na edição:', this.id);
  
      if (this.id) {
        this.cadastroService.listarCadastroPorId(this.id).subscribe({
          next: (cadastro) => {
            console.log('Dados recebidos da API:', cadastro);
            if (cadastro) {
              this.cadastroEditForm.patchValue(cadastro);
              this.carregando = false;
            } else {
              this.errorMessage = 'Pet não encontrado na API';
              this.carregando = false;
            }
          },
          error: (error) => {
            console.error('Erro na requisição:', error);
            this.errorMessage = 'Erro ao carregar dados do pet';
            this.carregando = false;
          }
        });
      } else {
        this.errorMessage = 'ID inválido';
        this.carregando = false;
      }
    });
  }

  carregarDadosCadastro(): void {
    this.cadastroService.listarCadastroPorId(this.id!).subscribe({
      next: (cadastro) => {
        this.cadastroEditForm.patchValue(cadastro);
        this.carregando = false;
      },
      error: (error) => {
        this.errorMessage = 'Erro ao carregar dados do pet';
        console.error('Erro:', error);
        this.carregando = false;
        this.snackBar.open('Erro ao carregar dados do pet', 'Fechar', {
          duration: 5000
        });
      }
    });
  }

  inicializarForm(): void {
    this.cadastroEditForm = new FormGroup({
      id: new FormControl({value: '', disabled: true}),
      tutor: new FormControl('', [Validators.required, Validators.minLength(3)]),
      emailTutor: new FormControl('', [Validators.required, Validators.email]),
      nome: new FormControl('', [Validators.required, Validators.minLength(2)]),
      raca: new FormControl('', [Validators.required]),
      idade: new FormControl('', [Validators.required, Validators.min(1)]),
      peso: new FormControl('', [Validators.required, Validators.min(0.1)]),
      cor: new FormControl('', [Validators.required]),
      descricao: new FormControl('', [Validators.required, Validators.maxLength(200)]),
      especie: new FormControl('', [Validators.required]),
      imagem: new FormControl('', [Validators.required])
    });
  }


salvar(): void {
  if (this.cadastroEditForm.valid && this.id) {
    this.carregando = true;
    
    const dadosAtualizados = {
      ...this.cadastroEditForm.value,
      id: this.id
    };

    this.cadastroService.alterarCadastro(this.id, dadosAtualizados).subscribe({
      next: () => {
        this.snackBar.open('Cadastro atualizado com sucesso!', 'Fechar', {
          duration: 5000
        });
        this.router.navigate(['/detalhes-pet', this.id]);
      },
      error: (error) => {
        console.error('Erro ao atualizar:', error);
        this.snackBar.open('Erro ao atualizar cadastro', 'Fechar', {
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
    Object.values(this.cadastroEditForm.controls).forEach(control => {
      control.markAsTouched();
    });
  }

  voltar(): void {
    if (this.id) {
      this.router.navigate(['/detalhes-pet', this.id]);
    } else {
      this.router.navigate(['/lista-cadastros']);
    }
  }
  
}
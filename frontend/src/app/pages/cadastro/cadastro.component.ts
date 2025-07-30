import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { DefaultPageLayoutComponent } from "../../components/default-page-layout/default-page-layout.component";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CadastroService } from '../../services/cadastro.service';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-cadastro',
  imports: [DefaultPageLayoutComponent, 
    ReactiveFormsModule,
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    MatRadioModule,
    MatSelectModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.scss'
})

export class CadastroComponent implements OnInit {

  cadastroForm!: FormGroup;
  
  constructor(private cadastroService: CadastroService, private snackBar: MatSnackBar){
  }

  ngOnInit(): void {
    this.cadastroForm = new FormGroup({
       tutor: new FormControl('', [Validators.required]),
       emailTutor: new FormControl('', [Validators.required, Validators.email]),
       nome: new FormControl('', [Validators.required]),
       raca: new FormControl('', [Validators.required]),
       idade: new FormControl([Validators.required]),
       peso: new FormControl([Validators.required]),
       cor: new FormControl('', [Validators.required]),
       descricao: new FormControl('', [Validators.required]),
       especie: new FormControl('',[Validators.required])
    });
  } 
  
  submit(){
    if(this.cadastroForm.valid) {
      this.cadastroService.cadastrarPet(this.cadastroForm.value).subscribe({
        next: (response) => console.log('Cadastro realizado com sucesso!', 
          this.snackBar.open('Pet cadastrado com sucesso!', 'Fechar', {
            duration: 5000
          }),
          this.cadastroForm.reset()),
      error: (error) => console.error('Erro ao cadastrar pet', error,
        this.snackBar.open('Erro ao cadastrar pet.', 'Fechar', {
          duration: 5000
        })),
      });
    }
  }
}
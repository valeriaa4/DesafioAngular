import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { CadastroService } from '../../services/cadastro.service';
import { CommonModule } from '@angular/common';
import { Cadastro } from '../../models/cadastro.model';
import { Observable } from 'rxjs';
import { CardComponent } from '../../components/card/card.component';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { BotaoComponent } from '../../components/botao/botao.component';

@Component({
  selector: 'app-lista-cadastros',
  imports: [HeaderComponent, 
    CommonModule, 
    CardComponent, 
    FormsModule, 
    MatFormFieldModule, 
    MatInputModule,
    MatIconModule,
    BotaoComponent],
  templateUrl: './lista-cadastros.component.html',
  styleUrl: './lista-cadastros.component.scss'
})

export class ListaCadastrosComponent {

  cadastros$ = new Observable<Cadastro[]>();
  
  constructor(private cadastroService: CadastroService, private router: Router){
    this.listarCadastros();
  }

  listarCadastros(){
    this.cadastros$ = this.cadastroService.listarCadastros();
  }

  deletarCadastro(id: number){
    this.cadastroService.deletarCadastro(id).subscribe(_ => this.listarCadastros());
  }

  detalhes(id: number): void {
    this.router.navigate(['/detalhes-pet', id]);
  }

  voltar(): void {
    this.router.navigate(['']);
  }
}

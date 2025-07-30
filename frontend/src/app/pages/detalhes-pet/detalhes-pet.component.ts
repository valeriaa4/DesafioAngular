import { Component, OnInit } from '@angular/core';
import { CadastroService } from '../../services/cadastro.service';
import { CommonModule, NgIf } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { HeaderComponent } from "../../components/header/header.component";
import { Cadastro } from '../../models/cadastro.model';
import { BotaoComponent } from "../../components/botao/botao.component";
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-detalhes-pet',
  imports: [NgIf, CommonModule, MatIconModule, HeaderComponent, BotaoComponent],
  templateUrl: './detalhes-pet.component.html',
  styleUrl: './detalhes-pet.component.scss'
})
export class DetalhesPetComponent implements OnInit {

  cadastros: any;
  isLoading = true;
  errorMessage: string | null = null;
  cadastro: Cadastro | null = null;
  
  constructor(private cadastroService: CadastroService, private route: ActivatedRoute,  private router: Router, private snackBar: MatSnackBar){
    
  }

  listarCadastrosPorId(id: number) {
    this.cadastroService.listarCadastroPorId(id);
  }

  alterar(): void {
    if (this.cadastros && this.cadastros.id) {
      console.log('Navegando para edição com ID:', this.cadastros.id);
      this.router.navigate(['/cadastro-edit', this.cadastros.id]);
    } else {
      console.error('ID do pet não disponível para edição');
      this.snackBar.open('ID do pet não disponível', 'Fechar', { duration: 3000 });
    }
  }

  deletar(id: number) {
    const snackBarRef = this.snackBar.open('Deseja excluir cadastro?', 'Sim', {
      duration: 10000
    });
  
    snackBarRef.onAction().subscribe(() => {
      this.cadastroService.deletarCadastro(id).subscribe(_ => this.listarCadastrosPorId(id));
      this.snackBar.open('Cadastro excluído com sucesso!', 'Fechar', {
        duration: 5000
      });
      this.voltar();
    });
    
    snackBarRef.afterDismissed().subscribe(info => {
      if (!info.dismissedByAction) {
        this.snackBar.open('Exclusão cancelada!', 'Fechar', {
          duration: 5000
        });
      }
    });
  }
    
  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    
    if (!idParam) {
      this.errorMessage = 'ID do pet não encontrado na URL';
      this.isLoading = false;
      return;
    }

    const id = +idParam;
    
    if (isNaN(id)) {
      this.errorMessage = 'ID do pet inválido';
      this.isLoading = false;
      return;
    }

    this.loadPetDetails(id);
  }

  private loadPetDetails(id: number): void {
    this.cadastroService.listarCadastroPorId(id).subscribe({
      next: (data) => {
        this.cadastros = data;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'Erro ao carregar detalhes do pet';
        console.error('Erro:', error);
        this.isLoading = false;
      }
    });
  }

  voltar(): void {
    this.router.navigate(['/lista-cadastros']);
  }
}
import { Component, EventEmitter, Input, Output} from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-card',
  imports: [MatIconModule, MatButtonModule],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})

export class CardComponent {
  @Input() nome: string = '';
  @Input() imagem: string = '';
  @Input() descricao: string = '';
  @Input() raca: string = '';
  @Input() tutor: string = '';
  @Input() emailTutor: string = '';
  @Input() peso: number = 0;
  @Input() cor: string = '';
  @Input() id: number = 0;
  @Input() idade: number = 0;
  @Input() especie: string = '';
  @Input() botaotxt: string = "";
  @Output("detalhes") onDetalhes = new EventEmitter();

  detalhes(id: number){
    this.onDetalhes.emit(id);
  }
}
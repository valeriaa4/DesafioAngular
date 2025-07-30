import { Component, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-botao',
  imports: [],
  templateUrl: './botao.component.html',
  styleUrl: './botao.component.scss'
})

export class BotaoComponent{
  @Input() botaotxt: string = '';
  @Input() metodo = new EventEmitter();
}
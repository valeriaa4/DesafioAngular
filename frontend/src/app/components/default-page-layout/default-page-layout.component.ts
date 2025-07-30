import { Component, EventEmitter, Input, Output } from '@angular/core';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-default-page-layout',
  imports: [HeaderComponent],
  templateUrl: './default-page-layout.component.html',
  styleUrl: './default-page-layout.component.scss'
})
export class DefaultPageLayoutComponent {
  @Input() titulo: string = "";
  @Input() botaotxt: string = "";
  @Output("submit") onSubmit = new EventEmitter();

  submit(){
    this.onSubmit.emit();
  }
}

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaNotificacoesComponent } from './lista-notificacoes.component';

describe('ListaNotificacoesComponent', () => {
  let component: ListaNotificacoesComponent;
  let fixture: ComponentFixture<ListaNotificacoesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaNotificacoesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaNotificacoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

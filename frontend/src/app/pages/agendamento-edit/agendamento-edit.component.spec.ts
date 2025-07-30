import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendamentoEditComponent } from './agendamento-edit.component';

describe('AgendamentoEditComponent', () => {
  let component: AgendamentoEditComponent;
  let fixture: ComponentFixture<AgendamentoEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AgendamentoEditComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgendamentoEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

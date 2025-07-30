import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CadastroComponent } from './pages/cadastro/cadastro.component';
import { ListaCadastrosComponent } from './pages/lista-cadastros/lista-cadastros.component';
import { AgendamentoComponent } from './pages/agendamento/agendamento.component';
import { DetalhesPetComponent } from './pages/detalhes-pet/detalhes-pet.component';
import { ListaAgendamentosComponent } from './pages/lista-agendamentos/lista-agendamentos.component';
import { CadastroEditComponent } from './pages/cadastro-edit/cadastro-edit.component';
import { ListaNotificacoesComponent } from './pages/lista-notificacoes/lista-notificacoes.component';
import { AgendamentoEditComponent } from './pages/agendamento-edit/agendamento-edit.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'cadastro',
        component: CadastroComponent
    },
    {
        path: 'lista-cadastros',
        component: ListaCadastrosComponent
    },
    {
        path: 'agendamento',
        component: AgendamentoComponent
    },
    {
        path: 'detalhes-pet/:id',
        component: DetalhesPetComponent
    },
    {
        path: 'lista-agendamentos',
        component: ListaAgendamentosComponent
    },
    {
        path: 'cadastro-edit/:id',
        component: CadastroEditComponent
    },
    {
        path: 'lista-notificacoes',
        component: ListaNotificacoesComponent
    },
    {
        path: 'agendamento-edit/:id',
        component: AgendamentoEditComponent
    },
];
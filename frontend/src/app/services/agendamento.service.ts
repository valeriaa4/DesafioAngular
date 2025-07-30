import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AgendamentoRequestDto } from '../models/agendamentorequestdto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { AgendamentoManual } from '../models/agendamentomanual.model';
import { AgendamentoAutomatico } from '../models/agendamentoautomatico.model';

@Injectable({
  providedIn: 'root'
})

export class AgendamentoService {

  private apiUrl = environment.agendamentoUrl;

  constructor(private httpClient: HttpClient) {
  }

  agendarCuidado(obj: AgendamentoRequestDto):Observable<AgendamentoRequestDto>{
    return this.httpClient.post<AgendamentoRequestDto>(this.apiUrl, obj);
  }

  // Agendamentos Manuais
  listarAgendamentosManuais() {
    return this.httpClient.get<AgendamentoManual[]>(`${this.apiUrl}/manual`);
  }

  listarAgendamentoManualPorId(id: number): Observable<any> {
    return this.httpClient.get<any>(`${this.apiUrl}/manual/${id}`);
  }
  
  deletarAgendamento(id: number) {
    return this.httpClient.delete<AgendamentoManual>(`${this.apiUrl}/${id}`);
  }

  alterarAgendamento(id: number, obj: AgendamentoManual):Observable<AgendamentoManual>{
    return this.httpClient.put<AgendamentoManual>(`${this.apiUrl}/${id}`, obj);
  }

  
  // Agendamentos Automáticos
  listarAgendamentosAutomaticos() {
    return this.httpClient.get<AgendamentoAutomatico[]>(`${this.apiUrl}/automatico`)
  }

  deletarAgendamentoAutomático(id: number) {
    return this.httpClient.delete<AgendamentoAutomatico>(`${this.apiUrl}/automatico/${id}`);
  }  

}

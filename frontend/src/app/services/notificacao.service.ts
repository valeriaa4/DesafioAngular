import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Notificacao } from '../models/notificacao.model';

@Injectable({
  providedIn: 'root'
})
export class NotificacaoService {

  private apiUrl = environment.notificacaoUrl;
  
  constructor(private httpClient: HttpClient) {
  }
  
  listarNotificacoes() {
    return this.httpClient.get<Notificacao[]>(this.apiUrl);
  }
}

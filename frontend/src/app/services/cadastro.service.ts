import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PetDtoRequest } from '../models/petdtorequest';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Cadastro } from '../models/cadastro.model';

@Injectable({
  providedIn: 'root'
})
export class CadastroService {
  
  private apiUrl = environment.cadastroUrl;

  constructor(private httpClient: HttpClient) {
  }

  cadastrarPet(obj: PetDtoRequest):Observable<PetDtoRequest>{
     return this.httpClient.post<PetDtoRequest>(this.apiUrl, obj);
  }

  listarCadastros() {
    return this.httpClient.get<Cadastro[]>(this.apiUrl);
  }

  listarCadastroPorId(id: number): Observable<any> {
    return this.httpClient.get<any>(`${this.apiUrl}/${id}`);
  }

  deletarCadastro(id: number) {
    return this.httpClient.delete<Cadastro>(`${this.apiUrl}/${id}`);
  }

  alterarCadastro(id: number, obj: Cadastro):Observable<Cadastro>{
    return this.httpClient.put<Cadastro>(`${this.apiUrl}/${id}`, obj);
  }
}

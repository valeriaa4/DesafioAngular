package com.desafiomicroservices.mscadastropets.controller;

import com.desafiomicroservices.mscadastropets.dtos.CatsBreedsDto;
import com.desafiomicroservices.mscadastropets.dtos.DogsBreedsDto;
import com.desafiomicroservices.mscadastropets.dtos.PetDtoRequest;
import com.desafiomicroservices.mscadastropets.enums.Especie;
import com.desafiomicroservices.mscadastropets.models.Pet;
import com.desafiomicroservices.mscadastropets.services.PetService;
import com.desafiomicroservices.mscadastropets.services.TheCatAPIService;
import com.desafiomicroservices.mscadastropets.services.TheDogAPIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;
    @Autowired
    private TheDogAPIService theDogAPIService;
    @Autowired
    private TheCatAPIService theCatAPIService;

    @Operation(summary = "Cadastrar Pet", description = "Cadastre um Pet. Obs.: Espécies possíveis: Cachorro / Gato (com iniciais maiúsculas).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na realização do cadastro / Requisição incorreta.")
    })
    @PostMapping
    Pet createPet(@Valid @RequestBody PetDtoRequest petDtoRequest){
        return petService.createPet(petDtoRequest);
    }

    @Operation(summary = "Listar Pets", description = "Acesse a lista de todos os pets cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso realizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na obtenção de dados / Requisição incorreta.")
    })
    @GetMapping
    List<Pet> listAll(){
        return petService.listAll();
    }

    @Operation(summary = "Listar Pet por Id", description = "Busque um pet cadastrado informando seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na obtenção de dados do ID informado / Requisição incorreta."),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado.")
    })
    @GetMapping("{id}")
    Optional<Pet> listById(@PathVariable Long id){
        return petService.listById(id);
    }

    @Operation(summary = "Filtrar por Raça", description = "Acesse a lista de pets por raça.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtro por raça aplicado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha ao aplicar filtro por raça / Requisição incorreta.")
    })
    @GetMapping("/filtro-raca")
    List<Pet> findByRaca(@RequestParam String raca){
        return petService.findByRaca(raca);
    }

    @Operation(summary = "Filtrar por Espécie", description = "Acesse a lista de pets por espécie (Cachorro / Gato).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtro por espécie aplicado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha ao aplicar filtro por espécie / Requisição incorreta.")
    })
    @GetMapping("/filtro-especie")
    List<Pet> findByEspecie(@RequestParam Especie especie){
        return petService.findByEspecie(especie);
    }


    @Operation(summary = "Atualizar Cadastro do Pet", description = "Atualize informações de um pet informando seu ID e todos os seus dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização cadastral realizada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na atualização do cadastro / Requisição incorreta."),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado.")
    })
    @PutMapping("{id}")
    List<Pet> updatePet(@PathVariable Long id, @RequestBody Pet pet){
        return petService.updatePet(id, pet);
    }

    @Operation(summary = "Excluir cadastro", description = "Exclua o cadastro de um pet informando seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exclusão realizada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na exclusão do cadastro do ID informado / Requisição incorreta."),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado.")
    })
    @DeleteMapping("{id}")
    List<Pet> deleteById(@PathVariable Long id){
        return petService.deleteById(id);
    }

    // rotas dog API

    @Operation(summary = "Listar raças (Dog)", description = "Lista de raças de Dogs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso!"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar lista (servidor).")
    })
    @GetMapping("/dog-api-racas")
    public ResponseEntity<List<DogsBreedsDto>> getRacasDog(){
        return ResponseEntity.ok(theDogAPIService.listarRacas());
    }

    @Operation(summary = "Imagens por raças (Dog)", description = "Retorna imagem da raça de acordo com o ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem retornada com sucesso!"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar imagem (servidor).")
    })
    @GetMapping("/dog-api-imagens")
    public ResponseEntity<?> getImagensPorRacasDog(@RequestParam("raca") Integer id,
                                                   @RequestParam("api_key") String apiKey){
        if (!theDogAPIService.isValidApiKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[ERRO] API Key Inválida");
        }
        return ResponseEntity.ok(theDogAPIService.pegarUrl(id, apiKey));
    }

    // rotas cat API
    @Operation(summary = "Listar raças (Cat)", description = "Lista de raças de Cats.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso!"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar lista (servidor).")
    })
    @GetMapping("/cat-api-racas")
    public ResponseEntity<List<CatsBreedsDto>> getRacasCat(){
        return ResponseEntity.ok(theCatAPIService.listarRacas());
    }

    @Operation(summary = "Imagens por raças (Cat)", description = "Retorna imagem da raça de acordo com o ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem retornada com sucesso!"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar imagem (servidor).")
    })
    @GetMapping("/cat-api-imagens")
    public ResponseEntity<?> getImagensPorRacasCat(@RequestParam("raca") String id,
                                                @RequestParam("api_key") String apiKey){
        if (!theCatAPIService.isValidApiKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[ERRO] API Key Inválida");
        }
        return ResponseEntity.ok(theCatAPIService.pegarUrl(id, apiKey));
    }
}
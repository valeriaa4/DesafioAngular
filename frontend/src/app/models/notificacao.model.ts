export type Notificacao = {
    idNotif: number,
    idPet: number,
    emailFrom: string,
    emailTutor: string,
    assunto: string,
    texto: string,
    dataEnvioEmail: string,
    statusEmail: string,
}
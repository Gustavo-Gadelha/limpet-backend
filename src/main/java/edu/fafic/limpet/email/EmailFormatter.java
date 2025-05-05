package edu.fafic.limpet.email;

import edu.fafic.limpet.dto.AppointmentDTO;

public class EmailFormatter {
    public static String confirmationEmail(AppointmentDTO dto) {
        return String.format("""
            Olá %s,

            Seu agendamento foi confirmado com sucesso!

            Detalhes do agendamento:
            - Pet: %s
            - Data: %s
            - Horário: %s
            - Telefone: (%s) %s
            - Observações: %s

            Agradecemos por escolher nosso serviço!
            """,
            dto.getClientName(),
            dto.getPetName(),
            dto.getDate(),
            dto.getTime(),
            dto.getDdd(),
            dto.getPhoneNumber(),
            dto.getRemarks() != null ? dto.getRemarks() : "Nenhuma observação"
        );
    }
}

package ru.perm.school9.gate.services

import ru.perm.school9.gate.model.dto.DTO

interface DTOConsumingFactory<PRODUCT, CONSUMED_DTO : DTO> : Factory<PRODUCT> {
    fun constructFromDTO(dto: CONSUMED_DTO): PRODUCT
}
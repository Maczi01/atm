package com.neueda.assignment.card;

import com.neueda.assignment.common.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardMapper implements Mapper<CardDTO, Card> {


    @Override
    public CardDTO mapToDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId());
        cardDTO.setBalance(card.getBalance());
        cardDTO.setOverdraft(card.getOverdraft());
        cardDTO.setAccountNumber(card.getAccountNumber());
        cardDTO.setPin(card.getPin());
        return cardDTO;
    }

    @Override
    public Card mapToEntity(CardDTO cardDTO) {
        Card card = new Card();
        card.setId(cardDTO.getId());
        card.setBalance(cardDTO.getBalance());
        card.setOverdraft(cardDTO.getOverdraft());
        card.setAccountNumber(cardDTO.getAccountNumber());
        card.setPin(cardDTO.getPin());
        return card;
    }

    @Override
    public List<CardDTO> mapListEntityToListDTO(List<Card> cardList) {
        return cardList.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<Card> mapListDTOToListEntity(List<CardDTO> cardDTOList) {
        return cardDTOList.stream().map(this::mapToEntity).collect(Collectors.toList());
    }
}

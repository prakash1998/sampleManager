package com.pra.controller;

import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.BaseEntityController;
import com.pra.model.PartyIn;
import com.pra.repositories.PartyInRepository;
import com.pra.view.PartyInWindow;

@Service
public class PartyInController extends BaseEntityController<PartyIn, Integer, PartyInRepository, PartyInWindow>{
	

}

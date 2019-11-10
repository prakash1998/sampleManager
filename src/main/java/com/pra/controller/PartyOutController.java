package com.pra.controller;

import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.BaseEntityController;
import com.pra.model.PartyOut;
import com.pra.repositories.PartyOutRepository;
import com.pra.view.PartyOutWindow;

@Service
public class PartyOutController extends BaseEntityController<PartyOut, Integer, PartyOutRepository, PartyOutWindow>{
	


}

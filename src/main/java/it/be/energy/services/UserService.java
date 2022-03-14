package it.be.energy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.energy.model.User;
import it.be.energy.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

}
//da implementare
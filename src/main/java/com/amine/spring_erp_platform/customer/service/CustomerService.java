package com.amine.spring_erp_platform.customer.service;

import com.amine.spring_erp_platform.customer.entity.Customer;
import com.amine.spring_erp_platform.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    // Règle métier 1 : Créer un client en vérifiant les doublons
    public Customer createCustomer(Customer customer) {
        if (repository.findByEmail(customer.getEmail()).isPresent()) {
            throw new RuntimeException("Opération refusée : Un client avec l'email " + customer.getEmail() + " existe déjà !");
        }
        return repository.save(customer);
    }

    // Règle métier 2 : Lister tous les clients
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    // Règle métier 3 : Trouver un client spécifique
    public Customer getCustomerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'ID : " + id));
    }
    // Règle métier 4 : Mettre à jour un client existant
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        // On vérifie d'abord si le client existe grâce à notre méthode précédente
        Customer existingCustomer = getCustomerById(id);

        // On met à jour les champs
        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        // (Note : on évite généralement de modifier l'email car c'est un identifiant fort)

        return repository.save(existingCustomer); // save() fait un UPDATE si l'ID existe déjà
    }

    // Règle métier 5 : Supprimer un client
    public void deleteCustomer(Long id) {
        Customer existingCustomer = getCustomerById(id);
        repository.delete(existingCustomer);
    }
}

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    
    @Autowired
    private ClientRepository clientRepository;
    
    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId)
        throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));
        return ResponseEntity.ok().body(client);
    }
    
    @PostMapping
    public Client createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long clientId,
        @Valid @RequestBody Client clientDetails) throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));
        
        client.setName(clientDetails.getName());
        client.setDateOfBirth(clientDetails.getDateOfBirth());
        client.setAddress(clientDetails.getAddress());
        client.setContactInformation(clientDetails.getContactInformation());
        final Client updatedClient = clientRepository.save(client);
        return ResponseEntity.ok(updatedClient);
    }
    
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long clientId)
        throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));
        
        clientRepository.delete(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
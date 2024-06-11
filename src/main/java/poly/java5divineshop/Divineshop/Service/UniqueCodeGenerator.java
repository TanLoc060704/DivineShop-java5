package poly.java5divineshop.Divineshop.Service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UniqueCodeGenerator {
    public String generateUniqueCode() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 8);
    }
}

package jongallant;

import io.github.cdimascio.dotenv.Dotenv;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.keys.KeyClientBuilder;
import com.azure.security.keyvault.keys.models.KeyType;
import com.azure.security.keyvault.keys.models.*;
import com.azure.security.keyvault.keys.KeyClient;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        
        // Load .env values into System Properties
        Dotenv.configure().systemProperties().load();

        System.out.print(System.getProperty("AZURE_CLIENT_ID"));

        // Build new DAC, which will read from System.Properties
        DefaultAzureCredential cred = new DefaultAzureCredentialBuilder().build();

        // Use DAC when constructing a Key Vault client.
        KeyClient keyClient = new KeyClientBuilder().vaultUrl(System.getProperty("AZURE_KEYVAULT_URL")).credential(cred)
                .buildClient();

        KeyVaultKey key = keyClient.createKey("key1", KeyType.RSA);

        System.out.printf("Key created. Name %s Id %s", key.getName(), key.getId());
    }
}

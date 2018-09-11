import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;

Credentials c = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL,"1", "description", "jenkins", "jenkins")

SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), c)
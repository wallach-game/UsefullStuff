import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.plugins.credentials.impl.*  // UsernamePasswordCredentialsImpl, etc.
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*  // contains BasicSSHUserPrivateKey
import com.cloudbees.jenkins.plugins.sshcredentials.*        // contains SSHUserPrivateKey
import org.jenkinsci.plugins.plaincredentials.*              // StringCredentials
import org.jenkinsci.plugins.plaincredentials.impl.*         // FileCredentials

def creds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
    com.cloudbees.plugins.credentials.Credentials.class,
    Jenkins.instance,
    null,
    null
)

creds.each { c ->
    println("-------------------------------------------------")
    println("ID:          ${c.id}")
    println("Description: ${c.description}")
    println("Type:        ${c.class}")

    if (c instanceof UsernamePasswordCredentials) {
        println("Username:    ${c.username}")
        println("Password:    ${c.password?.getPlainText()}")
    } else if (c instanceof StringCredentials) {
        println("Secret Text: ${c.secret?.getPlainText()}")
    } else if (c instanceof FileCredentials) {
        println("File Name:   ${c.fileName}")
        println("Secret File: ${new String(c.content, 'UTF-8')}")
    } else if (c instanceof com.cloudbees.jenkins.plugins.sshcredentials.SSHUserPrivateKey) {
        println("Username:    ${c.username}")
        println("PrivateKey:  ${c.privateKey}")
        println("Passphrase:  ${c.passphrase?.getPlainText()}")
    } else {
        println("Secret:      (Unknown type, might not be printable)")
    }
}

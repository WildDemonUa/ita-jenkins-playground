import hudson.model.*
import jenkins.model.*
import hudson.slaves.*
import hudson.slaves.EnvironmentVariablesNodeProperty.Entry
import hudson.plugins.sshslaves.SSHLauncher
import hudson.plugins.sshslaves.verifiers.*

// Pick one of the strategies from the comments below this line
SshHostKeyVerificationStrategy hostKeyVerificationStrategy = new NonVerifyingKeyVerificationStrategy()
    //= new KnownHostsFileKeyVerificationStrategy() // Known hosts file Verification Strategy
    //= new ManuallyProvidedKeyVerificationStrategy("<your-key-here>") // Manually provided key Verification Strategy
    //= new ManuallyTrustedKeyVerificationStrategy(false /*requires initial manual trust*/) // Manually trusted key Verification Strategy
    //= new NonVerifyingKeyVerificationStrategy() // Non verifying Verification Strategy, not secure :)

// Define a "Launch method": "Launch slave agents via SSH"
ComputerLauncher launcher = new SSHLauncher(
        "worker", // Host
        22, // Port
        "1", // Credentials ID
        (String)null, // JVM Options
        (String)null, // JavaPath
        (String)null, // Prefix Start Slave Command
        (String)null, // Suffix Start Slave Command
        (Integer)null, // Connection Timeout in Seconds
        (Integer)null, // Maximum Number of Retries
        (Integer)null, // The number of seconds to wait between retries
        hostKeyVerificationStrategy // Host Key Verification Strategy
)

// Define a "Permanent Agent"
Slave agent = new DumbSlave(
        "ssh-node",
        "/home/jenkins",
        launcher)
agent.nodeDescription = "Agent node description"
agent.numExecutors = 2
agent.labelString = "agent-node-label"
agent.mode = Node.Mode.NORMAL
agent.retentionStrategy = new RetentionStrategy.Always()

List<Entry> env = new ArrayList<Entry>();
env.add(new Entry("key1","value1"))
env.add(new Entry("key2","value2"))
EnvironmentVariablesNodeProperty envPro = new EnvironmentVariablesNodeProperty(env);

agent.getNodeProperties().add(envPro)

// Create a "Permanent Agent"
Jenkins.instance.addNode(agent)

return "Node has been created successfully."

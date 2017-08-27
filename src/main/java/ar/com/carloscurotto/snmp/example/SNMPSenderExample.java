package ar.com.carloscurotto.snmp.example;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMPSenderExample {

    public static void main(String[] args) throws Exception {
        // Create PDU
        PDU trap = new PDU();
        trap.setType(PDU.TRAP);

        OID oid = new OID("1.2.3.4.5");
        trap.add(new VariableBinding(SnmpConstants.snmpTrapOID, oid));
        trap.add(new VariableBinding(SnmpConstants.sysUpTime, new TimeTicks(5000))); // put your uptime here
        trap.add(new VariableBinding(SnmpConstants.sysDescr, new OctetString("System Description")));

        //Add Payload
        Variable var = new OctetString("testing snmp message");
        trap.add(new VariableBinding(oid, var));

        // Specify receiver
        Address targetaddress = new UdpAddress("localhost/9899");
        //Address targetaddress = new TcpAddress("localhost/9899");
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setVersion(SnmpConstants.version1);
        target.setAddress(targetaddress);

        // Send
        Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
        snmp.send(trap, target, null, null);
    }

}


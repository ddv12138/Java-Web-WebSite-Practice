[Interface]
PrivateKey = ${interface.privateKey}
Address = ${interface.address}
<#if interface.dns??>
    DNS = ${interface.dns}
</#if>

<#list peers as peer>
    [Peer]
    PublicKey = ${peer.publicKey}
    AllowedIPs = ${peer.allowedIPs}
    Endpoint = ${peer.endpoint}
</#list>
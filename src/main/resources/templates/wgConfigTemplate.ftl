[Interface]
PrivateKey = ${interface.privateKey}
Address = ${interface.address}
<#if interface.listenPort??>
    ListenPort = ${interface.listenPort?c}
</#if>
<#if interface.dns??>
    dns = ${interface.dns}
</#if>
<#if interface.postUp??>
    PostUp = ${interface.postUp}
</#if>
<#if interface.postDown??>
    PostDown = ${interface.postDown}
</#if>

<#list peers as peer>
    [Peer]
    PublicKey = ${peer.publicKey}
    AllowedIPs = ${peer.allowedIPs}
    <#if peer.endpoint??>
        Endpoint = ${peer.endpoint}
    </#if>
</#list>
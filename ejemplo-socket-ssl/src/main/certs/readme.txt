Hay dos directorios, servidor y cliente.
servidor contiene un almacen de claves serverKeys.jks y dentro un certificado de servidor.
La clave publica del servidor esta en ServerPublicKey.cer
Esta clave publica debe meterse dentro del almacen de claves de confianza del cliente, por lo que
esta clave esta tambien en el directorio client, dentro de su almacen de claves de confianza
clientTrustedCerts.jks

A continuacion el proceso para crear todos esto. Habria que repetir los mismos tres pasos para generar
la clave del cliente dentro de su almacen de claves (client/clientKey.jks), su clave pública 
(client/ClientPublicKey.cer) y al almacen de claves de confianza del servidor con la del cliente almacenada
dentro (server/serverTrustedCerts.jks)

Creacion de certificado del servidor metido en un almacen de certificados serverKey.jks

c:>keytool -genkey -keyalg RSA -alias serverKay -keystore serverKey.jks -storepass servpass
¿Cuáles son su nombre y su apellido?
  [Unknown]:  Server
¿Cuál es el nombre de su unidad de organización?
  [Unknown]:  Unidad
¿Cuál es el nombre de su organización?
  [Unknown]:  Organizacion
¿Cuál es el nombre de su ciudad o localidad?
  [Unknown]:  Ciudad
¿Cuál es el nombre de su estado o provincia?
  [Unknown]:  Estado
¿Cuál es el código de país de dos letras de la unidad?
  [Unknown]:  ES
  [no]:  si

Introduzca la contraseña de clave para <serverKay>
        (INTRO si es la misma contraseña que la del almacén de claves):

c:>

la password del certificado es importante que sea la misma que la del almacen. 

Para exportar la clave pública del servidor a un fichero

c:>keytool -export -keystore serverkey.jks -alias serverKey -file ServerPublic
Key.cer

Para meter la clave publica del servidor en los certificados de confianza del cliente.

c:>keytool -import -v -trustcacerts -alias serverKey -file ServerPublicKey.cer
 -keystore clientTrustedCerts.jks -keypass clientpass -storepass clientpass
Propietario: CN=Server, OU=Unidad, O=Organizacion, L=Ciudad, ST=Estado, C=ES
Emisor: CN=Server, OU=Unidad, O=Organizacion, L=Ciudad, ST=Estado, C=ES
Número de serie: 8421719
Válido desde: Sat Apr 18 13:10:58 CEST 2015 hasta: Fri Jul 17 13:10:58 CEST 2015

Huellas digitales del Certificado:
         MD5: 1E:D2:BC:79:10:48:8F:FD:FD:D5:7B:7B:6C:50:F0:DB
         SHA1: 5C:D3:3E:4B:45:0B:CA:90:F3:9F:3B:96:B3:20:DB:1A:DE:9F:57:EE
         SHA256: 08:84:31:7E:27:89:94:93:5A:DE:22:A0:37:E2:02:67:E4:50:34:DE:AE:
DD:29:2B:67:CB:68:05:B1:94:D7:00
         Nombre del Algoritmo de Firma: SHA256withRSA
         Versión: 3

Extensiones:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: D1 85 CB EB FF 6A 53 08   0E 9E EB FB 65 3A C2 B6  .....jS.....e:..
0010: E4 A2 27 23                                        ..'#
]
]

¿Confiar en este certificado? [no]:  si
Se ha agregado el certificado al almacén de claves
[Almacenando clientTrustedCerts.jks]

Nuevamente es importante misma clave para certificado (-keypass) y alamacen (-storepass)
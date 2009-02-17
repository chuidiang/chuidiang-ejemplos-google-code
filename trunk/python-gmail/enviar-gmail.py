# Javier Abellan. 17 Feb 2009
# Ejemplo de envio de un correo simple con python y gmail.

import smtplib
import mimetypes

from email.MIMEText import MIMEText
from email.Encoders import encode_base64

# Construimos el mensaje simple
mensaje = MIMEText("""Este es el mensaje
de las narices""")
mensaje['From']="usuario@gmail.com"
mensaje['To']="destinatario@gmail.com"
mensaje['Subject']="Tienes un correo"

# Establecemos conexion con el servidor smtp de gmail
mailServer = smtplib.SMTP('smtp.gmail.com',587)
mailServer.ehlo()
mailServer.starttls()
mailServer.ehlo()
mailServer.login("usuario@gmail.com","password")

# Envio del mensaje
mailServer.sendmail("usuario@gmail.com",
		"destinatario@gmail.com",
		mensaje.as_string())

# Cierre de la conexion
mailServer.close()

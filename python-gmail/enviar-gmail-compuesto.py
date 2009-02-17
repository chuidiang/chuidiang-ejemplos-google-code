# Javier Abellan. 17 Febrero 2009
# Ejemplo de envio de un mail con imagen adjunta con python y gmail.

import smtplib
import mimetypes

from email.MIMEMultipart import MIMEMultipart
from email.MIMEText import MIMEText
from email.MIMEImage import MIMEImage
from email.Encoders import encode_base64

# Construimos un mensaje Multipart, con un texto y una imagen adjunta
mensaje = MIMEMultipart()
mensaje['From']="usuario@gmail.com"
mensaje['To']="destinatario@gmail.com"
mensaje['Subject']="Tienes un correo"

# Adjuntamos el texto
mensaje.attach(MIMEText("""Este es el mensaje
de las narices"""))

# Adjuntamos la imagen
file = open("Chuidiang-64.gif", "rb")
contenido = MIMEImage(file.read())
contenido.add_header('Content-Disposition', 'attachment; filename = "Chuidiang-64.gif"')
mensaje.attach(contenido)

# Establecemos conexion con el servidor smtp de gmail
mailServer = smtplib.SMTP('smtp.gmail.com',587)
mailServer.ehlo()
mailServer.starttls()
mailServer.ehlo()
mailServer.login("usuario@gmail.com","password")

# Enviamos el correo, con los campos from y to.
mailServer.sendmail("usuario@gmail.com",
		"destinatario@gmail.com",
		mensaje.as_string())

# Cierre de la conexion
mailServer.close()

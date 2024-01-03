You need to create key.jks file for ssl verification to configure spring with elasticsearch. For creating key.jks file first you need to open https://localhost:9200. View certificates on this server then export crt file.
After exporting this file you should make key.jks file with keytool. To create this file "keytool -importcert -file "certificate.cer" -keystore key.jks -alias "<anything>"" write this command to cmd. Be sure that your cmd has java bin file on envioremt variable
Then store them in project.

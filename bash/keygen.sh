#!/usr/bin/env bash
# create rsa key-pair in /erib/Business/test-it-resources/keys
# after creation a new pair SbTelecomPhonesTest will fail!
parent='../src/test/resources'
cd ${parent}
keys='keys'
mkdir -p ${keys}
if [ "$(ls -A ${keys})" ]; then echo keys already exist; exit
else echo `make keys:`
    cd keys
    openssl genrsa -out private_key.pem 1024
    openssl pkcs8 -topk8 -inform PEM -outform DER -in private_key.pem -out private_key.der -nocrypt
    openssl rsa -in private_key.pem -pubout -outform DER -out public_key.der
    openssl rsa -in private_key.pem -pubout -outform PEM -out public_key.pem
    ls
fi
#!/usr/bin/env bash
source ./env.sh

openssl pkeyutl -decrypt -inkey ${PARENT}/${KEYS_DIR}/private_key.pem -in encrypted_message.bin -out decrypted_message.txt
cat decrypted_message.txt
echo
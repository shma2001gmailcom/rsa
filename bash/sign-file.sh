#!/usr/bin/env bash
source ./env.sh
echo "This is the file to sign" > file_to_sign.txt
openssl dgst -sha256 -sign ${PARENT}/${KEYS_DIR}/private_key.pem -out file_signature.bin file_to_sign.txt
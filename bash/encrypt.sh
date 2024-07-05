#!/usr/bin/env bash

source ./env.sh
cat "text-to-encrypt" | openssl pkeyutl -encrypt -inkey ${PARENT}/${KEYS_DIR}/public_key.pem -pubin -out encrypted_message.bin
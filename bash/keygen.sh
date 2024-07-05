#!/usr/bin/env bash
# create rsa key-pair in /erib/Business/test-it-resources/keys
# after creation a new pair SbTelecomPhonesTest will fail!
set -euo pipefail
parent='../src/test/resources'

# Function to display error messages
error() {
    echo "Error: $1" >&2
    exit 1
}

# Ensure OpenSSL is installed
command -v openssl >/dev/null 2>&1 || error "OpenSSL is not installed. Please install it and try again."

# Variables
PARENT=${1:-"${parent}"} # Allow parent directory to be specified as an argument
KEYS_DIR='keys'
KEY_SIZE=${2:-8192} # Allow key size to be specified as an argument, default to 8192

cd "${PARENT}" || error "Failed to change directory to ${PARENT}"
mkdir -p "${KEYS_DIR}"
if [ "$(ls -A "${KEYS_DIR}")" ]; then
    echo "Keys already exist"
    exit 0
else
    echo "Generating keys:"
    cd "${KEYS_DIR}" || error "Failed to change directory to ${KEYS_DIR}"
    openssl genrsa -out private_key.pem "${KEY_SIZE}"
    # Convert private key to PKCS#8 format (DER)
    openssl pkcs8 -topk8 -inform PEM -outform DER -in private_key.pem -out private_key.der -nocrypt
    # Extract public key in DER format
    openssl rsa -in private_key.pem -pubout -outform DER -out public_key.der
    # Extract public key in PEM format
    openssl rsa -in private_key.pem -pubout -outform PEM -out public_key.pem
    ls -tal
fi


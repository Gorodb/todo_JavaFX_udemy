#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

# Check if gradlew exists
if [[ ! -x "./gradlew" ]]; then
  echo "Error: ./gradlew not found or not executable."
  exit 1
fi

# Run Gradle with continuous build
./gradlew run --continuous
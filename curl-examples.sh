#!/usr/bin/env bash

# Start the application first:
#   mvn spring-boot:run
#
# Every curl command uses -i so the HTTP status line and response headers are
# printed together with the response body. -v also prints the HTTP request.

BASE_URL="${BASE_URL:-http://localhost:8080}"
SEPARATOR="--------------------------------------------------------------------------------"
CURL_OPTIONS=(-i -v --no-progress-meter)

echo
echo "${SEPARATOR}"
echo "### List categories"
curl "${CURL_OPTIONS[@]}" "${BASE_URL}/api/categories"

echo
echo "${SEPARATOR}"
echo "### List all pets"
curl "${CURL_OPTIONS[@]}" "${BASE_URL}/api/pets"

echo
echo "${SEPARATOR}"
echo "### List pets filtered by category"
curl "${CURL_OPTIONS[@]}" "${BASE_URL}/api/pets?categoryId=1"

echo
echo "${SEPARATOR}"
echo "### Get one pet"
curl "${CURL_OPTIONS[@]}" "${BASE_URL}/api/pets/1"

echo
echo "${SEPARATOR}"
echo "### Create a pet"
curl "${CURL_OPTIONS[@]}" -X POST "${BASE_URL}/api/pets" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "name": "Flocke",
    "species": "Meerschweinchen",
    "dateOfBirth": "2025-11-03",
    "price": 39.90,
    "available": true,
    "categoryId": 1
  }'

echo
echo "${SEPARATOR}"
echo "### Update a pet"
curl "${CURL_OPTIONS[@]}" -X PUT "${BASE_URL}/api/pets/1" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "name": "Momo",
    "species": "Zwergkaninchen",
    "dateOfBirth": "2025-09-14",
    "price": 54.90,
    "available": true,
    "categoryId": 1
  }'

echo
echo "${SEPARATOR}"
echo "### Delete a pet"
curl "${CURL_OPTIONS[@]}" -X DELETE "${BASE_URL}/api/pets/2"

echo
echo "${SEPARATOR}"
echo "### Try to get the deleted pet"
curl "${CURL_OPTIONS[@]}" "${BASE_URL}/api/pets/2"

echo
echo "${SEPARATOR}"

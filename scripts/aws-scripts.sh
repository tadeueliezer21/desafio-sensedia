#!/bin/bash

aws --endpoint-url=http://localhost:4566 s3api create-bucket --bucket files-for-processing --region us-east-1 --acl public-read --output table | cat

aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name send-notification --region us-east-1 --output table | cat

aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name file-processing-queue --region us-east-1 --output table | cat

aws --endpoint-url=http://localhost:4566 dynamodb create-table --cli-input-json file://FileInfoPayload.json  --output table | cat

aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn  arn:aws:sns:us-east-1:000000000000:process-notify  --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:send-notification --output table | cat
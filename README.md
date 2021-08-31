# Lambda - Java Maven Project
This is a Lambda project with Java Run time

## Requirements
	- maven
	- java
	- git
	- aws cli


# Build local
	- mvn clean package

# run local
	- java -jar target/emporiaenergy-client-1.0-SNAPSHOT.jar

# Deploy to AWS

	# Step 1 - Run CloudFormation Package command
	aws cloudformation package --template-file cf-emporia-client-lambda.yml --output-template-file target/cf-emporia-client-lambda.yml --s3-bucket <YOUR_BUCKET_NAME> --force-upload

	# Step 2 - Run CloudFormation Deploy command
	aws cloudformation deploy --template-file target/cf-emporia-client-lambda.yml --stack-name emporia-client-lambda --capabilities CAPABILITY_IAM --parameter-overrides S3BucketName=<YOUR_BUCKET_NAME>
	




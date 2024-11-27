dependencies {
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.batch:spring-batch-test")
    implementation(project(":enterpark-ticket-core:domain"))
}

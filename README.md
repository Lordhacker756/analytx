# Analytx 📊
## The perfect collective intern analysis tool!

Analytx is a comprehensive backend system designed to facilitate the evaluation and analysis of interns within an organization. It enables employees to rate interns across various domains, providing detailed feedback and generating insightful reports to aid in decision-making processes.

## Features

- **Multi-Domain Evaluation**: Employees can rate interns across eight different domains, each with its own set of subcategories.
- **Personal Remarks**: Users can include personal remarks alongside their ratings, providing additional context and insights.
- **Report Generation**: Analytx generates detailed reports with charts, offering a visual representation of intern performance.
- **Individual Score Details**: The system provides a table with individual scores and all feedback, allowing for in-depth analysis of each intern.
- **Links Management**: Integration with various links for additional reference or documentation.

## Upcoming features
- **PDF Download**: Download the reports as PDF
- **Email support**: Send the reports directly to your email

## Technologies Used

- **Spring Boot 6**: Backend framework for building robust and scalable applications.
- **MySQL**: Relational database management system for data storage.
- **Hibernate**: Object-relational mapping framework for simplified database interactions.
- **Spring Data JPA**: Simplifies the implementation of JPA-based repositories.
- **Swagger UI**: Tool for API documentation and exploration.
- **Spring Actuators**: Monitoring and management endpoints for the backend.
- **Docker**: Containerization platform for easy deployment and scalability.

## Getting Started

To run Analytx locally, follow these steps:

1. Clone the repository to your local machine.
2. Navigate to the project directory.

```bash
git clone <repository_url>
cd analytx
```

3. Ensure you have Docker installed on your system.
4. Execute the `docker-compose.yaml` file to deploy both the database and backend.

```bash
docker-compose up
```

5. Once the containers are up and running, MySQL can be accessed at `localhost:3333`, and the backend will be available at `localhost:8085`.

6. All the endpoints available can be easily viewed at `http://localhost:8085/swagger-ui/index.html#/`

## Contributing

Contributions to Analytx are welcome! If you find any issues or have suggestions for improvements, please feel free to submit a pull request or open an issue on the GitHub repository.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Special thanks to the developers and contributors of the technologies used in this project.
- Inspired by the need for comprehensive intern evaluation tools in organizational settings.
# Contributing

Thanks for your interest in contributing! Keep contributions small and focused.

Basic workflow:

1. Fork the repository.
2. Create a branch for your change: `git checkout -b feature/your-feature`.
3. Make changes and add tests where appropriate.
4. Run the app and tests locally:

```powershell
.\mvnw.cmd -DskipTests package
```

5. Commit with clear messages and open a pull request against the `main` branch.

Code style:

- Follow Spring Boot conventions. Use Lombok where present.
- Keep methods small and add meaningful unit tests.

Issue tracking:

- Open an issue for major changes or design discussions before implementation.

License and CLA:

- By contributing, you agree your changes will be made available under this repository's license.

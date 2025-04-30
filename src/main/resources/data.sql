INSERT INTO user (id, name, email, password, creation_date, modif_date, last_login)
VALUES
    ('550e8400-e29b-41d4-a716-446655440000', 'Juan Pérez', 'juan.perez@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('550e8400-e29b-41d4-a716-446655440001', 'María López', 'maria.lopez@example.com', 'securepass456', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
    ('550e8400-e29b-41d4-a716-446655440002', 'Carlos García', 'carlos.garcia@example.com', 'mypassword789', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO phones (id, id_user, country_code, city_code, number)
VALUES
    ('550e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440000', 34, 91, 123456789),
    ('550e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440001', 34, 93, 987654321),
    ('550e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440002', 34, 95, 555555555);
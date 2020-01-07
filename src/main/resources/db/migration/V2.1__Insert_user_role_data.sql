
insert into role (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
('Admin', '/', 'Y', 'Y', 'Y', 'Y'),
('review manager', '/review', 'Y', 'Y', 'Y', 'N'),
('business manager ', '/business', 'Y', 'Y', 'Y', 'N'),
('customer user', '/customer', 'Y', 'Y', 'Y', 'Y'),
('review user', '/review', 'Y', 'Y', 'Y', 'Y')

;
commit;


insert into customer_role values
(1, 1),
(2, 2),
(2, 3),
(3, 4),
(3, 5)

;
commit;
-- # ORGANISATION TEST DATA #
INSERT INTO organisation (name, full_name, inn, kpp, address, phone)
    VALUES ('Art of flowers', 'LLC "Art of flowers"', '1294516859', '345511225', '410015, Россия, г. Саратов, ул. Дзержинского, 12', '89279236287');
INSERT INTO organisation (name, full_name, inn, kpp, address, phone)
    VALUES ('SmartAuto', 'LLC "SmartAuto"', '4091526808', '305401325', '410038, Россия, г. Саратов, ул. Посадского, 180/198', '89272298277');
INSERT INTO organisation (name, full_name, inn, kpp, address, phone, is_active)
    VALUES ('MobileDev', 'LLC "MobileDev"', '8190821009', '909002961', '410039, Россия, г. Саратов, ул. Волжская, 24/26', '89270918570', FALSE);

-- # OFFICE TEST DATA #
INSERT INTO office (name, address, phone, org_id)
    VALUES ('Head office', '410015, Россия, г. Саратов, ул. Дзержинского, 12', '89279236287', 1);
INSERT INTO office (name, address, phone, org_id)
    VALUES ('Head office', '410038, Россия, г. Саратов, ул. Посадского, 180/198', '89272298277', 2);
INSERT INTO office (name, address, phone, org_id)
    VALUES ('Car repair shop #1', '410023, Россия, г. Саратов, ул. Большая Горная, 136', '89271820098', 2);
INSERT INTO office (name, address, phone, org_id)
    VALUES ('Car repair shop #2', '410062, Россия, г. Саратов, ул. Соколовогорская, 9', '89271919182', 2);
INSERT INTO office (name, address, phone, is_active, org_id)
    VALUES ('Car repair shop #3', '410049, Россия, г. Саратов, ул. Рабочая, 158', '89275890981', FALSE, 2);
INSERT INTO office (name, address, phone, is_active, org_id)
    VALUES ('MobileDev', '410039, Россия, г. Саратов, ул. Волжская, 24/26', '89270918570', FALSE, 3);

-- # DOC TEST DATA #
INSERT INTO doc (code, name) VALUES (3, 'Свидетельство о рождении');
INSERT INTO doc (code, name) VALUES (7, 'Военный билет');
INSERT INTO doc (code, name) VALUES (8, 'Временное удостоверение, выданное взамен военного билета');
INSERT INTO doc (code, name) VALUES (10, 'Паспорт иностранного гражданина');
INSERT INTO doc (code, name) VALUES (11, 'Свидетельство о рассмотрении ходатайства о признании лица беженцем на территории Российской Федерации по существу');
INSERT INTO doc (code, name) VALUES (12, 'Вид на жительство в Российской Федерации');
INSERT INTO doc (code, name) VALUES (13, 'Удостоверение беженца');
INSERT INTO doc (code, name) VALUES (15, 'Разрешение на временное проживание в Российской Федерации');
INSERT INTO doc (code, name) VALUES (18, 'Свидетельство о предоставлении временного убежища на территории Российской Федерации');
INSERT INTO doc (code, name) VALUES (21, 'Паспорт гражданина Российской Федерации');
INSERT INTO doc (code, name) VALUES (23, 'Свидетельство о рождении, выданное уполномоченным органом иностранного государства');
INSERT INTO doc (code, name) VALUES (24, 'Удостоверение личности военнослужащего Российской Федерации');
INSERT INTO doc (code, name) VALUES (91, 'Иные документы');

-- # COUNTRY TEST DATA #
INSERT INTO country (code, name) VALUES (643, 'Российская Федерация');
INSERT INTO country (code, name) VALUES (804, 'Украина');
INSERT INTO country (code, name) VALUES (112, 'Республика Беларусь');
INSERT INTO country (code, name) VALUES (398, 'Республика Казахстан');
INSERT INTO country (code, name) VALUES (860, 'Республика Узбекистан');
INSERT INTO country (code, name) VALUES (762, 'Республика Таджикистан');
INSERT INTO country (code, name) VALUES (51, 'Республика Армения');
INSERT INTO country (code, name) VALUES (268, 'Грузия');
INSERT INTO country (code, name) VALUES (031, 'Республика Азербайджан');
INSERT INTO country (code, name) VALUES (417, 'Киргизская Республика');
INSERT INTO country (code, name) VALUES (440, 'Литовская Республика');
INSERT INTO country (code, name) VALUES (428, 'Латвийская Республика');
INSERT INTO country (code, name) VALUES (233, 'Эстонская Республика');
INSERT INTO country (code, name) VALUES (498, 'Республика Молдова');
INSERT INTO country (code, name) VALUES (795, 'Туркменистан');

-- # USER TEST DATA #
INSERT INTO public.user (first_name, second_name, middle_name, position, phone, doc_number, doc_date, doc_code, citizenship_code, is_identified, org_id, off_id)
    VALUES ('Иван', 'Домрычев', 'Сергеевич', 'Генеральный директор', '89271837495', '6310987789', '12-21-1990', 21, 643, TRUE, 1, 1);
INSERT INTO public.user (first_name, second_name, middle_name, position, phone, doc_number, doc_date, doc_code, citizenship_code, is_identified, org_id, off_id)
    VALUES ('Светлана', 'Галиева', 'Николаевна', 'Продавец', '89271098266', '6300181001', '05-18-1974', 21, 643, TRUE, 1, 1);
INSERT INTO public.user (first_name, second_name, middle_name, position, phone, doc_number, doc_date, doc_code, citizenship_code, is_identified, org_id, off_id)
    VALUES ('Семён', 'Повольный', 'Семёнович', 'Генеральный директор', '89279820989', '6301009918', '10-10-1975', 21, 643, TRUE, 2, 2);
INSERT INTO public.user (first_name, second_name, middle_name, position, phone, doc_number, doc_date, doc_code, citizenship_code, is_identified, org_id, off_id)
    VALUES ('Николай', 'Иванов', 'Иванович', 'Автомеханик', '89271299292', '6301009918', '12-13-1970', 21, 643, TRUE, 2, 3);
INSERT INTO public.user (first_name, second_name, middle_name, position, phone, doc_number, doc_date, doc_code, citizenship_code, is_identified, org_id, off_id)
    VALUES ('Пётр', 'Сидоров', 'Михайлович', 'Автомеханик', '89270991489', '6301023870', '02-19-1970', 21, 643, TRUE, 2, 4);
INSERT INTO public.user (first_name, second_name, middle_name, position, phone, doc_number, doc_date, doc_code, citizenship_code, is_identified, org_id, off_id)
    VALUES ('Арсений', 'Футулов', 'Николаевич', 'Автомеханик', '89271908297', '6305780987', '01-29-1978', 21, 643, TRUE, 2, 5);
INSERT INTO public.user (first_name, second_name, middle_name, position, phone, doc_number, doc_date, doc_code, citizenship_code, is_identified, org_id, off_id)
    VALUES ('Пётр', 'Забывалов', 'Евгеньевич', 'Автомеханик', '89271239182', '6309000123', '04-11-1988', 21, 643, TRUE, 2, 5);
INSERT INTO public.user (first_name, second_name, middle_name, position, phone, doc_number, doc_date, doc_code, citizenship_code, org_id, off_id)
    VALUES ('Евгений', 'Верный', 'Валерьевич', 'Генеральный директор', '89271237182', '6310182789', '01-01-1992', 21, 643, 3, 6);
INSERT INTO public.user (first_name, second_name, middle_name, position, phone, doc_number, doc_date, doc_code, citizenship_code, org_id, off_id)
    VALUES ('Игорь', 'Разработный', 'Павлович', 'Android разработчик', '89272900928', '6310189801', '11-12-1995', 21, 643, 3, 6);
INSERT INTO public.user (first_name, second_name, middle_name, position, phone, doc_number, doc_date, doc_code, citizenship_code, org_id, off_id)
    VALUES ('Алексей', 'Быстряк', 'Игоревич', 'iOS разработчик', '89272220910', '6310900990', '03-12-1996', 21, 643, 3, 6);

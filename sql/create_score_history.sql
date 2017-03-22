CREATE TABLE score_history (
    ecosystem_id int unsigned NOT NULL,
    day_number int unsigned NOT NULL,
    raw_score int unsigned NOT NULL,
    PRIMARY KEY (ecosystem_id, day_number)
);
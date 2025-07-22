CREATE TABLE IF NOT EXISTS recommendation_history (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    login VARCHAR NOT NULL,
    track TEXT NOT NULL,
    recommended_track_ids TEXT[] NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
